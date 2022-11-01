To prepare the application for work, you need:
# First step: 
you need to create mysql database with name 'car_rental_spring';

    CREATE DATABASE car_rental_spring;
# Second step: 
you need to customize your application.properties file:

{


  #for connect with your MySQL server
  
    spring.datasource.username=

    spring.datasource.password=
  
  #for javaMailSender
    spring.mail.host=

    spring.mail.username=

    spring.mail.password=

    spring.mail.port=

    spring.mail.protocol=

    mail.debug=
  
}
