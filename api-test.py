import urllib
import urllib2
import base64
import sys

if len (sys.argv) == 4 :
  authToken = sys.argv[1]
  host = sys.argv[2] 
  username = sys.argv[3] 
  headers = {"Content-Type":"application/json"}

  api_routes = sorted({'followers.json','followers.xml','following.json','following.xml','tweets.json','tweets.xml'})
#Test for routes
  for route in api_routes:
    try:
      url = "http://"+host+"/twitter-api/api/"+username+"/"+route+"?token="+authToken
      print "Testing "+route+":"
      request = urllib2.Request(url)
      response = urllib2.urlopen(request)
      http_status = response.getcode()
      print http_status
    except urllib2.HTTPError, e:
      print "Error testing "+route+" : "+format(e.code)+" - "+format(e.reason)




else:
  print "\n\n\n\nUsage: python api-test.py TOKEN HOST:PORT USERNAME \n\n\n"
