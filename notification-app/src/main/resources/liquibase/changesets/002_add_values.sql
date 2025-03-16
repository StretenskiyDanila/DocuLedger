truncate table notification;

insert into notification(user_id,user_name,user_mail,tab_name,state)
values (1,'Danila','stretenskijdanila@gmail.com','Начисление','RUNNING'),
       (1,'Danila','stretenskijdanila@gmail.com','Акт зачисления','RUNNING'),
       (1,'Danila','stretenskijdanila@gmail.com','Акт выполненных работ','FINISHED');