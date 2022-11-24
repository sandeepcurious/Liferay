create index IX_96B250F3 on GBS_Entry (groupId, guestbookId);
create index IX_4564A8E5 on GBS_Entry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_655C24A7 on GBS_Entry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F2ECB36A on GBS_Guestbook (groupId);
create index IX_346DDB34 on GBS_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_965B9EB6 on GBS_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);