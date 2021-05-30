#!/usr/bin/env python
# coding: utf-8

# ### first question
# Kindly open this web page: 
# https://www.premierleague.com/stats/top/players/goals?se=-1
# 
# and use Selenium to extract a dataset consists of: <br>
# players names, rank, number of goals, and their nationalities <br>

# In[96]:


from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import pandas as pd
import time


# In[97]:


driver = webdriver.Chrome()
driver.get('https://www.premierleague.com/stats/top/players/goals?se=-1')
driver.maximize_window()


# In[98]:


#players names

players = driver.find_elements_by_xpath('//a[@class="playerName"]')
names = []
for p in range(len(players)):
    names.append(players[p].text)


#rank class="rank"
players = driver.find_elements_by_xpath('//td[@class="rank"]')
ranks = []
for p in range(len(players)):
    ranks.append(players[p].text)
#number of goals mainStat text-centre
players = driver.find_elements_by_xpath('//td[@class="mainStat text-centre"]')
goals = []
for p in range(len(players)):
    goals.append(players[p].text)

#nationalities span playerCountry

players = driver.find_elements_by_xpath('//span[@class="playerCountry"]')
nationalities = []
for p in range(len(players)):
    nationalities.append(players[p].text)

driver.close()


# In[100]:


top20scorrer = pd.DataFrame(list(zip(names,ranks,goals,nationalities)),
                         columns=['Name','Rank','number_of_goals' , 'nationality'])
top20scorrer


# In[101]:


top20scorrer.set_index('Name').to_csv('top20scorrer.csv')


# ### second one 
# Kindly check the attached "players_stats.json" and <br>
# work on converting it to a Pandas dataframe then <br>
# work on extracting a dataset consists of: <br>
#  players names, height, appearances, wins, losses, <br>
# goals per match ratio, assists, yellow cards, red cards <br>

# In[102]:


playersData = pd.read_json(r'PLAYERJASON.json')
playersData


# In[103]:


playersData.set_index('Name').to_csv('playersData.csv')


# ### third one
# Kindly merge the above two datasets <br>
# o By using Pandas or Orange <br>
# o If you do both, you will get BONUS <br>

# In[104]:


Full_data = playersData.merge(top20scorrer ,how = 'right', on = "Name")
Full_data.set_index('Rank' , inplace=True)
Full_data


# ![image.png](attachment:image.png)

# ![image.png](attachment:image.png)

# ### fourth
# For the merged dataset, please use Orange to <br>
# visualize the relation between number of matched <br>
# played (Appearances) and the number of scored <br>
# goals <br>
# 

# ![VIs.png](attachment:VIs.png)

# ![image.png](attachment:image.png)
