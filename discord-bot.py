import discord

client = discord.Client()
bot_token = 'NjgwODg2ODU5Njg5MDk5Mjg1.XlGbyg.nFWrle-fOAGRU9F4dvfZuJD43IM'


@client.event
async def on_ready():
    print('We have logged in as {0.user}'.format(client))


@client.event
async def on_message(message):
    if message.author == client.user:
        return

    if message.content.startswith('$hello'):
        await message.channel.send('Hello!')

client.run(bot_token)