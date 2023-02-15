#!/bin/bash
sshpass -f ./password/custom_auth_user_pass mysql -u custom_auth_user -p < ./sql/recreate_tables.sql