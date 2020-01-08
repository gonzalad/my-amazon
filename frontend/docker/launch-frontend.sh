#!/bin/bash -e

RED='\033[0;31m'
NC='\033[0m'
SCRIPT=$(readlink -f $0)
SCRIPT_PATH=$(dirname $SCRIPT)

# Traitement des erreurs
set -o pipefail  # trace les erreurs des pipes
set -o errtrace  # trace les erreurs dans les fonctions appelées
function error() {
    JOB=$(basename $0)
    if [ -z $2 ];
    then
        echo -e "${RED}ERREUR dans ${JOB} : $1${NC}" 1>&2
    else
        echo -e "${RED}ERREUR dans ${JOB} : ligne $1 avec le code d'erreur $2${NC}" 1>&2
    fi
    exit 1
}
trap 'error ${LINENO} ${?}' ERR

httpd-foreground
