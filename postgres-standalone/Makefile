# Override namespace with `make all NAMESPACE=release` or such
# Version is the unix timestamp.
# Steps required for building an image:
# 0) if using docker-machine: docker-machine start and eval $(docker-machine env)
# 1) docker login
# 2) make build to create the image
# 3) make push to push it
NAMESPACE ?= $(USER)
REPOSITORY ?= arsatiki/worldcon-dev
VERSION=$(shell git describe)
IMAGE=$(REPOSITORY):postgres-$(VERSION)

all: build

build:
	docker build --pull -t $(IMAGE) .

push:
	docker push $(IMAGE)

.PHONY: build tag push clean