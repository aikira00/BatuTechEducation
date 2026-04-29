drop database if exists dbusers; 
create database dbusers;
use dbusers;



create table users (
username varchar(10) primary key,
password varchar(64) not null,
role varchar(5),
constraint cst_role check(role='admin' or role='user')
);

password è 64 perchè è crittografata
insert into users values ('pippo',sha2('password',256),'user');

select password=sha2('password',256) from users;

# Ruolo 
per ora nulla

# Token

per adesso: come gestiamo, una volta autenticati, il discorso del token?

variabile statica come Lista di interi

static List<Integer> token = new ArrayList<>();
dopo che siamo aunteticati si genera il token all'interno di un while 
in cui continuo a generare token fino a che non ne genero uno che non c'è 

static Random r = new Random();
int token = r.nextInt(1000000000, 2000000001);

Due metodi uno per POST e uno per GET
POST: due parametri da form user e pwd (se a db ci sono creaimo token e lo restituiamo)
GET: invia il token nell'head con bearer token




