import smtplib
import mimetypes 
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.base import MIMEBase
from email import encoders 
import os
import time


fromaddr = "coloque-email-de-origem-aqui" 

toaddr = ['email-de-detino-1','email-de-destino-2']

msg = MIMEMultipart() 

msg['From'] = fromaddr 
msg['To'] = ', '.join(toaddr)
msg['Subject'] = "Hardsearch Alertas - Contratos"

body = ('''Atenção! Existem contratos próximos do vencimento ou vencidos. Você deve acessar o sistema para varificar os mesmos.

Enviado por: Alertas Hardsearch - alerta_contra v0.20.0522''')
msg.attach(MIMEText(body)) 

filename = r'C:\Hardsearch\alertaMail\contra_a_vencer\arq.txt' 
attachment = open(filename, "rb") 

mimetypes_anexo = mimetypes.guess_type(filename)[0].split('/') 
part = MIMEBase(mimetypes_anexo[0],mimetypes_anexo[1]) 

part.set_payload((attachment).read())
encoders.encode_base64(part)
part.add_header('Content-Disposition', "attachment; filename= %s"% filename)

msg.attach(part)

server = smtplib.SMTP('smtp.configuracao-do-smtp.com',587) 
server.starttls() 
server.login(fromaddr, open(r'C:\Hardsearch\alertaMail\senha.txt').read().strip()) 
text = msg.as_string() 
server.sendmail(fromaddr, toaddr, text)
server.quit()


