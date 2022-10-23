import os
import re
import sys

import gpxpy
import requests
from geojson import MultiLineString

"""
Importer for gpx files:
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


def geojson_from_file(content: str):
    gpx = gpxpy.parse(content)
    points = []

    for track in gpx.tracks:
        for segment in track.segments:
            for point in segment.points:
                points.append((point.longitude, point.latitude))

    return gpx, MultiLineString(points)


def send_request_and_get_guid(gpx, source_file, geojson):
    body = {
        "name": gpx.name,
        "timestamp": str(gpx.time.isoformat()),
        # "gpx": base64.b64encode(source_file.encode("utf-8")).decode("utf-8"),
        # "geojson": base64.b64encode(str(geojson).encode("utf-8")).decode("utf-8")
        "gpx": source_file,
        "geojson": str(geojson)
    }

    # print(body)
    response = requests.post(api_enpoint, json=body)
    return response.json()


def process_files():
    regex = r"[\d\.\s,-]*\.gpx"
    for filename in os.listdir(source_dir):
        matches = re.findall(regex, filename)
        if len(matches) != 1:
            print("Skipping ", filename)
            continue

        print("Processing ", filename)
        with open(os.path.join(source_dir, filename), "r") as f:
            text = f.read()
            gpx, geojson = geojson_from_file(text)
            response = send_request_and_get_guid(gpx, text, geojson)
            print("\tCreated track. ID: ", response["id"])

            # TODO: Move the processed files to the Archive directory


if __name__ == "__main__":
    process_args()
    process_files()
