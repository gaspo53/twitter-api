import urllib
import urllib2
import base64
import sys

if len (sys.argv) == 4 :
  authToken = sys.argv[1]
  host = sys.argv[2] 
  username = sys.argv[3] 
  url = "http://"+host+"/twitter-api/api/"+username+"/followers.json?token="+authToken
  headers = {"Content-Type":"application/json"}
  request = urllib2.Request(url)
   
  # post form data
  # request.add_data(urllib.urlencode(data))
   
  response = urllib2.urlopen(request)
   
  print response.info().headers
  print response.read()
else:
  print "\n\n\n\nUsage: python api-test.py TOKEN HOST:PORT USERNAME \n\n\n"
