package com.curious.guestbook.search;

public interface EntryBatchReindexer {
	public void reindex(long guestbookId,long companyId);
}
