#python3

import json
import sys

def validate(data):
    if "status" in data:
        if data["status"] == 500:
            error = ""
            if "error" in data:
                error = data["error"]
            else:
                error = data
            print("\033[91m {}\033[00m".format(f"failed due to '{error}'"))

if __name__ == "___main___":
    file = open(sys.argv[0])
    data = json.load(file)
    file.close()
    validate(data)