Luciano dos Passos Almeida
Manual de Implantação ProjetoWebService

Implantando projeto no Jboss-as-7.1.1.Final

Primeiro passo

Configurar o driver do Postgre

Na pasta do Jboss entre em modules/com
e crie uma pasta chamada postgre e dentro desse diretório crie uma pasta chamada main.
Copie o driver do postgre para dentro dessa pasta main.

Crie um arquivo com o nome module.xml com o seguinte conteudo:

<?xml version="1.0" encoding="UTF-8"?>

<!--
> ~ JBoss, Home of Professional Open Source.
> ~ Copyright 2010, Red Hat, Inc., and individual contributors
> ~ as indicated by the @author tags. See the copyright.txt file in the
> ~ distribution for a full listing of individual contributors.
> ~
> ~ This is free software; you can redistribute it and/or modify it
> ~ under the terms of the GNU Lesser General Public License as
> ~ published by the Free Software Foundation; either version 2.1 of
> ~ the License, or (at your option) any later version.
> ~
> ~ This software is distributed in the hope that it will be useful,
> ~ but WITHOUT ANY WARRANTY; without even the implied warranty of
> ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
> ~ Lesser General Public License for more details.
> ~
> ~ You should have received a copy of the GNU Lesser General Public
> ~ License along with this software; if not, write to the Free
> ~ Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
> ~ 02110-1301 USA, or see the FSF site: http://www.fsf.org.
> -->


&lt;module xmlns="urn:jboss:module:1.1" name="com.postgre"&gt;



> 

&lt;resources&gt;


> > 

&lt;resource-root path="postgresql-9.3-1100.jdbc4.jar"/&gt;


> > <!-- Insert resources here -->

> 

&lt;/resources&gt;


> 

&lt;dependencies&gt;


> > 

&lt;module name="javax.api"/&gt;


> > 

&lt;module name="javax.transaction.api"/&gt;


> > 

&lt;module name="javax.servlet.api" optional="true"/&gt;



> 

&lt;/dependencies&gt;




&lt;/module&gt;



Pronto o driver do postgre está configurado.

Agora é necessário criar um datasource e declarar o driver no arquivo de configurações do Jboss o standalone.xml
Partindo da pasta do Jboss entre em standalone\configuration e abra o arquivo chamado standalone.xml
Nesse arquivo dentro da tag 

&lt;drivers&gt;

 declare o seguinte driver:


&lt;driver name="postgre" module="com.postgre"&gt;


> 

&lt;xa-datasource-class&gt;

org.postgresql.Driver

&lt;/xa-datasource-class&gt;




&lt;/driver&gt;



Após feito isso nesse arquivo dentro da tag 

&lt;datasources&gt;

 declare o seguinte datasource:



&lt;datasource jndi-name="java:jboss/alunos" pool-name="alunosPostgre" enabled="true" use-java-context="true"&gt;


> 

&lt;connection-url&gt;

jdbc:postgresql:postgres

&lt;/connection-url&gt;


> 

&lt;driver&gt;

postgre

&lt;/driver&gt;


> 

&lt;security&gt;


> > 

&lt;user-name&gt;

postgres

&lt;/user-name&gt;


> > 

&lt;password&gt;

root

&lt;/password&gt;



> 

&lt;/security&gt;




&lt;/datasource&gt;



Nesse data source configure o user name e o password de acordo com o do postgre que estiver usando.

Após feito essa configuração já é possível rodar a aplicação.
Lembrando que é necessario no postgre criar um banco de dados chamado Bd\_Alunos ou mudar o nome do banco de dados na tag 

&lt;connection-url&gt;

 do datasource declarado anteriormente.Além de criar a tabela nesse banco da seguinte maneira:

create table public.tb\_alunos(
> matricula varchar(255) not null,
> nome varchar(255) not null,
> nota float not null,
> primary key(matricula)
);
