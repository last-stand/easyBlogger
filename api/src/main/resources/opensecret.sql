create database if not exists opensecret;
use opensecret;

create table if not exists user_info(
        id int unsigned AUTO_INCREMENT primary key,
        username varchar(50) NOT NULL unique,
        email varchar(80) NOT NULL unique,
        password varchar(200) NOT NULL,
        authority varchar(10) NOT NULL default 'ROLE_USER',
        enabled boolean default 1,
        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    ) engine = InnoDb;

insert into user_info(username,email,password,authority,enabled)
    values('admin','admin@email.com','$2a$10$fG4OkCOUGkIF1SEsnwxmz.73z052QRLlbfkPZllKpxYOlhcpwzLwy','ROLE_ADMIN',1);
insert into user_info(username,email,password,authority,enabled)
    values('user','user@email.com','$2a$10$fG4OkCOUGkIF1SEsnwxmz.73z052QRLlbfkPZllKpxYOlhcpwzLwy','ROLE_USER',1);
commit;