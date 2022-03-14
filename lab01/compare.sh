#!/bin/bash
diff -w <(head -n $2 $1) <(head -n $2 first_million_primes.txt)>/dev/null;REPLY=$?

if [ ${REPLY} -eq 0 ]
then
         echo "Files are identical"
else
         echo "Files are different"
fi

