#!/usr/bin/env bash
set -e

# Try to get the latest tag
if git describe --tags --abbrev=0 >/dev/null 2>&1; then
  LATEST_TAG=$(git describe --tags --abbrev=0)
else
  LATEST_TAG="v0.0.0"
fi

# Remove "v"
LATEST_VERSION="${LATEST_TAG#v}"

IFS='.' read -r MAJOR MINOR PATCH <<< "$LATEST_VERSION"

# If this is the first release, avoid invalid git log
if [ "$LATEST_TAG" = "v0.0.0" ]; then
  COMMITS=$(git log --pretty=format:"%s")
else
  COMMITS=$(git log "${LATEST_TAG}"..HEAD --pretty=format:"%s")
fi

# Default bump: patch
BUMP="patch"

if echo "$COMMITS" | grep -q "feat!"; then
  BUMP="major"
elif echo "$COMMITS" | grep -q "feat:"; then
  BUMP="minor"
elif echo "$COMMITS" | grep -q "fix:"; then
  BUMP="patch"
fi

case $BUMP in
  major)
    MAJOR=$((MAJOR + 1))
    MINOR=0
    PATCH=0
    ;;
  minor)
    MINOR=$((MINOR + 1))
    PATCH=0
    ;;
  patch)
    PATCH=$((PATCH + 1))
    ;;
esac

NEW_VERSION="${MAJOR}.${MINOR}.${PATCH}"

echo "$NEW_VERSION"
