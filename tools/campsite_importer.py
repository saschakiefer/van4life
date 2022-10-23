import json
import os
import re
import sys

import requests

"""
Importer for files generated from Drafts with the following structure:

2022-08-23 - Campspot.txt
{
	"date": "2022-08-23",
	"timestamp": "2022-08-23-17-05-52",
	"latitude": 2.289602,
	"longitude": 5.240288,
	"rating": 0,
	"name": ""
}
----
<comment>
"""

source_dir = ""
api_enpoint = ""
archive_dir = ""


def process_args():
    if len(sys.argv) < 3:
        print("Parameters missing. Call importer.py <source directory> <api endpoint>")

    global source_dir
    global api_enpoint
    global archive_dir

    source_dir = sys.argv[1]
    api_enpoint = sys.argv[2]
    archive_dir = source_dir + "/Archive"

    print("Using files from ", source_dir)
    print("Sending to ", api_enpoint)
    print("Archiving files to ", archive_dir)
    print()


def json_body_from_file(content: str):
    sections = content.split("----")

    body = json.loads(sections[0])
    body["comment"] = sections[1][1:]

    if "type" not in body:
        print("\tSetting type to CS")
        body["type"] = "CS"

    body["position"] = {"latitude": body["latitude"], "longitude": body["longitude"]}

    body["visits"] = [body["date"]]

    return body


def send_request_and_get_guid(body):
    response = requests.post(api_enpoint, json=body)
    return response.json()


def process_files():
    regex = r"\d\d\d\d-\d\d-\d\d\s-\sCampspot.txt"
    for filename in os.listdir(source_dir):
        matches = re.findall(regex, filename)
        if len(matches) != 1:
            print("Skipping ", filename)
            continue

        print("Processing ", filename)
        with open(os.path.join(source_dir, filename), 'r') as f:
            text = f.read()
            body = json_body_from_file(text)
            # print("\t", json_body_from_file(text))
            response = send_request_and_get_guid(body)
            print("\tCreated campsite. ID: ", response["id"])

            # TODO: Move the processed files to the Archive directory


if __name__ == '__main__':
    process_args()
    process_files()
