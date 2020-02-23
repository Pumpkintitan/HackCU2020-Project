from flask import Flask
from markupsafe import escape
import twitter

auth = open("../twitterauth")

api = twitter.Api(consumer_key=auth.readline().strip(),
                  consumer_secret=auth.readline().strip(),
                  access_token_key=auth.readline().strip(),
                  access_token_secret=auth.readline().strip())

app = Flask(__name__)

uname = ""


@app.route('/<location>')
def grab_location(location):
    # show the user profile for that user
    status = api.PostUpdate("The user is currently at: ", location)
    return '%s' % escape(location)


app.run(host="0.0.0.0", port=5002)
