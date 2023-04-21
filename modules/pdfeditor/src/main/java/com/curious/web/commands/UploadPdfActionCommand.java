package com.curious.web.commands;

import com.curious.web.constants.PdfEditorPortletKeys;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + PdfEditorPortletKeys.PDFEDITOR,
		"mvc.command.name=upload" }, service = MVCActionCommand.class)
public class UploadPdfActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		try {
			log.info("came here");
			ServiceContext serviceContext = ServiceContextFactory.getInstance(UploadPdfActionCommand.class.getName(),
					actionRequest);
			editPdf(serviceContext, uploadPortletRequest, actionRequest, actionResponse);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
		}
		return false;
	}

	private void editPdf(ServiceContext serviceContext, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws FileNotFoundException, IOException, PortalException {
		log.info("came here");
		File uploadedFile = uploadPortletRequest.getFile("file");
		long x = ParamUtil.getLong(actionRequest, "x");
		long y = ParamUtil.getLong(actionRequest, "y");
		long width = ParamUtil.getLong(actionRequest, "width", 120);
		long height = ParamUtil.getLong(actionRequest, "height", 30);
		log.info("file name: " + uploadedFile.getName());
		ByteArrayOutputStream formOutStream = new ByteArrayOutputStream();
		PdfWriter pdfWriter = new PdfWriter(formOutStream);
		PdfReader pdfReader = new PdfReader(uploadedFile);
		PdfDocument pdf = new PdfDocument(pdfReader, pdfWriter);

		PdfFormField personal = PdfFormField.createEmptyField(pdf);
		personal.setFieldName("subscription_number");
		PdfTextFormField name = PdfFormField.createText(pdf, new Rectangle(x, y, width, height), "subscription_number",
				"");
		personal.addKid(name);
		PdfAcroForm.getAcroForm(pdf, true).addField(personal, pdf.getFirstPage());
		pdf.close();
		// http://localhost:8080/documents/37729/44503/form.pdf/fbe5ed4d-a872-f1cc-8968-890511f0babe?t=1682073014313&download=true
		FileEntry fileEntry = _dlAppLocalService.getFileEntry(37729, 44503, "form.pdf");
		_dlAppLocalService.updateFileEntry(serviceContext.getUserId(), fileEntry.getFileEntryId(), "form.pdf",
				ContentTypes.APPLICATION_PDF, "form.pdf", "", null, DLVersionNumberIncrease.NONE,
				formOutStream.toByteArray(), serviceContext);
		log.info("finished");
	}

	@Reference
	private DLAppLocalService _dlAppLocalService;

	private static Log log = LogFactoryUtil.getLog(UploadPdfActionCommand.class);
}
