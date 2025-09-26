# Loads csv files as DataFrames, then concatenates and prints them.
import os
from pathlib import Path
import pandas as pd

def concat_csv_files(start_path: Path, indent: int = 0, extension=".csv"):
    dataframes = {}
    df_count = 1
    
    for root, dirs, files in os.walk(start_path, topdown=True):
        filtered_files = [f for f in files if f.endswith(extension)]    
        if filtered_files:
            for f in filtered_files:
                full_path = os.path.join(root, f)
                try:
                    df = pd.read_csv(full_path)
                    dataframes[f'df{df_count}'] = df
                    print(f"Loaded {f} as df{df_count}")
                    df_count += 1
                except Exception as e:
                    print(f"Error reading {full_path}: {e}")
    return dataframes

path = Path("/home/user/Documents")

if path.exists():
    dataframes = concat_csv_files(path)
    combined = pd.concat(dataframes, axis=0, ignore_index=False)
    print(combined.head())
else:
    print(f"{path}: Does not exist.")
