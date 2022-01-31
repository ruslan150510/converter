# ConverterApplication
Using the Spring framework, it is necessary to develop a service for
currency conversion and collecting transaction statistics.
API: /exchange, /stats.

/exchange
Request: user id, amount in source currency, source currency, target currency.
Answer: request id, amount in the target currency.
You can use external APIs for conversion or to get the conversion rate.

/stats
Providing access to selective information on request.
Examples of requests:
Users who requested a conversion of more than $ 10,000 per request.
Users whose total requested volume is more than $100,000.
Rating of currency conversion directions by popularity.
