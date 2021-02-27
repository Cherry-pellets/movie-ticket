import bs4
import requests
# from bs4 import BeautifulSoup  
from bs4 import BeautifulSoup
# response = requests.get('https://www.baidu.com')  
response = requests.get('https://www.baidu.com')
re_text = response.text
re_content = response.content

print(re_text)
print(type(re_text))
print(re_content)
print(type(re_content))
response.encoding = 'utf-8'
# re_text = response
# re_text = response.text  
# re_content = response.content  
# print (re_text)  
# print (type(re_text))  
# print (re_content)  
# print (type(re_content))  
# response.encoding = 'utf-8'  
# re_text = respon