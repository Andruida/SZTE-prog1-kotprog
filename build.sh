#!/bin/bash

TMPDIR=$(mktemp -d)
LOCALDIR=$(pwd)


cp -r * $TMPDIR
cd $TMPDIR

rm -rf .git .gitignore *.sh *.md *.zip *.pdf *.png .vscode/ lib/ docs/ prog1/kotprog/dontstarve/tests
zip -r feladat.zip *

mv feladat.zip $LOCALDIR

cd $LOCALDIR

rm -rf $TMPDIR