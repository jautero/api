FROM node:6.2

# Eventually these would go to a volume
RUN mkdir -p /api/logs

COPY app.js package.json /api/app/
COPY bin /api/app/bin/
COPY lib /api/app/lib/
COPY static /api/app/static/
COPY templates /api/app/templates

# TODO chown
# RUN groupadd -r hakkapeliitta && \
#    useradd -r -g hakkapeliitta hakkapeliitta && \
#    chown -R hakkapeliitta:hakkapeliitta /hakkapeliitta/


WORKDIR /api/app

# In the future, we'll break this into two images:
# - First will contain only `package.json` and run `npm install`.
# - Second will build on that and only contain the code.
# It's only necessary to update the first one if the deps change.

# Set default values for development
ENV API_ROOT=https://api.worldcon.fi\
    CORS_ORIGIN="https://www.worldcon.fi http://www.worldcon.fi"\
    DATABASE_URL=postgres://localhost:5432/worldcon75\
    SESSION_SECRET="dev secret"\
    SENDGRID_APIKEY=""

RUN npm install
CMD [ "npm", "start" ]
