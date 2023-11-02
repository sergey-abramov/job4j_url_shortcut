alter table site drop column url_id;
alter table url add column site_id int references site(id);