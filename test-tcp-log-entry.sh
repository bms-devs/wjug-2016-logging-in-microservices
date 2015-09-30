#!/bin/zsh

echo "{\"message\":\"this is a test message logged over tcp\", \"extraField1\": \"value1\", \"extraField2\": \"value2\"}" | netcat -c "$(docker-machine ip default)" 7777 

