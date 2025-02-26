
import pandas as pd
import random

passenger_names = ["Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hank", "Ivy", "Jack"]
pickup_locations = ["New York", "Los Angeles", "Houston", "Seattle", "Dallas", "Austin", "San Diego", "Denver", "Chicago", "Miami"]
destinations = ["San Francisco", "Chicago", "Miami", "Boston", "Las Vegas", "Denver", "Phoenix", "Atlanta", "Washington DC", "Orlando"]
ride_data = {
    "Passenger Name": [random.choice(passenger_names) for _ in range(100000)],
    "Pickup Location": [random.choice(pickup_locations) for _ in range(100000)],
    "Destination": [random.choice(destinations) for _ in range(100000)]
}

df = pd.DataFrame(ride_data)
df.to_csv("ride_requests_100000.csv", index=False)

print("Ride dataset with 1,000,000 rows created")

