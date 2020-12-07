# Answer Digital - Automation Test - Andrew West
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time


# Initialise the webdriver
try:
	options = webdriver.firefox.options.Options()
	options.add_argument('--headless')
	driver = webdriver.Firefox(options=options)
except:
	options = webdriver.chrome.options.Options()
	options.add_argument('--headless')
	driver = webdriver.Chrome(options=options)


## Test Case 1
# "To perform Test case 1, please click on **form authentication on Menu**
# Test case 1: Automate Form Authentication"
correct_username = 'tomsmith'
correct_password = 'SuperSecretPassword!'
wrong_username = 'andrewwest'
wrong_password = 'password1234'

def login(username, password):
	driver.get('http://the-internet.herokuapp.com/')
	driver.find_element(By.LINK_TEXT, 'Form Authentication').click()
	driver.find_element(By.NAME, 'username').send_keys(username)
	driver.find_element(By.NAME, 'password').send_keys(password)
	driver.find_element(By.TAG_NAME, 'button').click()
	return driver.find_element(By.ID, 'flash').text

### Scenario 1
# "Try to login with correct username and wrong password and assert login validation."
print( login(correct_username, wrong_password) )
# Out:  Your password is invalid! \n ×

### Scenario 2
# "Try to login with wrong username and correct password and assert login validation."
print( login(wrong_username, correct_password) )
# Out:  Your username is invalid! \n ×

### Scenario 3
# "Try to login with correct username and correct password and then logout."
print( login(correct_username, correct_password) )
driver.find_element(By.LINK_TEXT, 'Logout').click()
# Out:  You logged into a secure area! \n ×


## Test Case 2
# "To perform Test case 2, please click on **Infinite scroll on Menu**
# Test case 2: scroll to the bottom of the page twice and scroll back to the top of the page and assert 'Infinite Scroll' text"
driver.get('http://the-internet.herokuapp.com/')
driver.find_element(By.LINK_TEXT, 'Infinite Scroll').click();  time.sleep(1)
driver.find_element(By.TAG_NAME, 'body').send_keys(Keys.END);  time.sleep(1)
driver.find_element(By.TAG_NAME, 'body').send_keys(Keys.END);  time.sleep(1)
driver.find_element(By.TAG_NAME, 'body').send_keys(Keys.HOME)
print( driver.find_element(By.CLASS_NAME, 'jscroll-inner').text )
# Out:
# \n Temmpore et nam...
# \n Architecto sit...
# \n Dolores delectus...
# \n Et accusamus...


## Test Case 3
# "To perform Test case 3, please click on **Key presses on Menu**
# Test case 3: Click on 4 keys and assert the text displayed after every key press"
driver.get('http://the-internet.herokuapp.com/')
driver.find_element(By.LINK_TEXT, 'Key Presses').click()
for key in ['A', '1', Keys.ALT, Keys.F9]:
	driver.find_element(By.TAG_NAME, 'body').send_keys(key)
	print( driver.find_element(By.ID, 'result').text )
# Out:
# You entered: A
# You entered: 1
# You entered: ALT
# You entered: F9


# End
driver.quit()
