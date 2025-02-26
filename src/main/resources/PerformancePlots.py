import matplotlib.pyplot as plt
import numpy as np

# Data extracted from images
labels = ["100000 dataset", "100000 dataset (Dynamic Space Allocation)", "10000 dataset"]
ride_list_times = [23.899, 8.696, 0.171]  # Time taken for RideListPerformanceTest
ride_circular_queue_times = [0.085, 0.088, 0.010]  # Time taken for RideCircularQueuePerformanceTest
ride_queue_times = [0.074, 0.063, 0.011]  # Time taken for RideQueuePerformanceTest

x = np.arange(len(labels))  # X-axis positions
width = 0.2  # Width of bars

fig, ax = plt.subplots(figsize=(9, 5))

# Plot bars
bars1 = ax.bar(x - width, ride_list_times, width, label="RideListPerformanceTest")
bars2 = ax.bar(x, ride_circular_queue_times, width, label="RideCircularQueuePerformanceTest")
bars3 = ax.bar(x + width, ride_queue_times, width, label="RideQueuePerformanceTest")

# Set logarithmic scale
ax.set_yscale("log")

# Labeling
ax.set_xlabel("Dataset Size and Type")
ax.set_ylabel("Execution Time (seconds) [Log Scale]")
ax.set_title("Performance Test Comparison (Log Scale)")
ax.set_xticks(x)
ax.set_xticklabels(labels, rotation=15)
ax.legend()

# Add value labels on bars
for bars in [bars1, bars2, bars3]:
    for bar in bars:
        height = bar.get_height()
        ax.text(bar.get_x() + bar.get_width() / 2, height, f"{height:.3f}",
                ha='center', va='bottom', fontsize=10)

# Display the plot
plt.show()
