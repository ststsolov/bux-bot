# bux-bot

Connects to the beta bux environment, listens for quotes and opens/closes a position
with the specified product when the product's price reaches the specified values.

# build
mvn clean package

# run
java -jar target/bux-bot.jar productId entryPrice takeProfitPrice stopLossPrice 

e.g. java -jar target/bux-bot.jar sb26502 1.163 1.164 1.162

buys EURUSD at 1.163, sells at 1.164 or 1.162
