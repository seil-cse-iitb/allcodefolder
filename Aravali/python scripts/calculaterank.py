
from pymongo import MongoClient
import math
import json
import time
import datetime
import sys
import numpy as np
import operator
client=MongoClient('127.0.0.1:27017')
db=client.test3
db2 = client.rank
hashmap={}

collectionLocal = db2["ranknew"]


for flat_name in db.collection_names():
   if flat_name[:7] == 'aravali':
      flat = db[flat_name]
   else:
      continue
   energy=[]
   TS=[]
   if flat_name=="aravali_217":
      continue
   flat=db[flat_name]
   now = datetime.datetime.now()
   epochOfStartMonth = (datetime.datetime(now.year,now.month,1,0,0) - datetime.datetime(1970,1,1)).total_seconds()

   for datum in flat.find({'TS':{'$gt':epochOfStartMonth}},{'con':1,'_id':0,'TS':1,'Power':1}).sort('TS',1).limit(1):
      minCon = datum['con']

   for datum in flat.find({},{'con':1,'_id':0}).sort('TS',-1).limit(1):
      maxCon = datum['con']
   #print(flat_name)
   try:
      unitsConsumed = maxCon - minCon
      hashmap[flat_name] = unitsConsumed/1000
   except:
      print("error", sys.exc_info()[0])

   #print(hashmap)
sorted_x = sorted(hashmap.items(), key=operator.itemgetter(1))
   #print(sorted_x)
i=0
for key,value in sorted_x:
   i = i+1
   collectionLocal.update({"Meter_Id": int(key[8:])}, {"$set": {"con": value}})
   print(key[8:] , "   ",value)

for i in range(0,5):
    j=1
    for datum in collectionLocal.find({'labels':i}).sort('con',1):
        collectionLocal.update({"Meter_Id": datum['Meter_Id']}, {"$set": {"rank": j}})
        j=j+1
