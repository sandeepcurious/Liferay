create index IX_96B250F3 on GBS_Entry (groupId, guestbookId);
create index IX_1E2E207F on GBS_Entry (groupId, status);
create index IX_BC8E2F17 on GBS_Entry (status);
create index IX_4564A8E5 on GBS_Entry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_655C24A7 on GBS_Entry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D1DE850 on GBS_Guestbook (groupId, status);
create index IX_82942826 on GBS_Guestbook (status);
create index IX_346DDB34 on GBS_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_965B9EB6 on GBS_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);