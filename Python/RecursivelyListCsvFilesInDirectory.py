# Prints names of directories and CSV files only
import os
from pathlib import Path

def list_files_and_dirs(start_path: Path, indent: int = 0, extension=".csv"):
    for root, dirs, files in os.walk(path, topdown=True):
        level = root.replace(str(path), "").count(os.sep)
        indent = "    " * level
        print(f"{indent}{os.path.basename(root)}/")

      sub_indent = "    " * (level + 1)
        filtered_files = [f for f in files if f.endswith(extension)]
        if filtered_files:
            for f in filtered_files:
                print(f"{sub_indent}File: {f}")
        else:
            print(f"{sub_indent}(no {extension} files)")

path = Path("/home/user/Documents")

if path.exists():
   list_files_and_dirs(path)
else:
    print(f"{path}: Does not exist.")
