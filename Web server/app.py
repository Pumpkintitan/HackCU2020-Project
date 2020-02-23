import datetime
from urllib.parse import unquote

import twitter
from flask import Flask

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
    loc = unquote(location).replace("+", " ")

    if loc == "Nowhere":
        return
    status = api.PostUpdate(f"The user is currently at: {loc} at {datetime.datetime.now()}")
    return loc


app.run(host="0.0.0.0", port=5002)
