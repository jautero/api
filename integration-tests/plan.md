# Member Services API
## Public information
### `GET /api/kansa/public/people`
### `GET /api/kansa/public/stats`
## Authentication
### `POST /api/kansa/key`
### `GET/POST /api/kansa/login`
### `GET/POST /api/kansa/logout`
## User info
### `GET /api/kansa/user`
### `GET /api/kansa/user/log`
## Member info
### `GET /api/kansa/people`
### `POST /api/kansa/people`
### `GET /api/kansa/people/:id`
### `GET /api/kansa/people/:id/log`
### `POST /api/kansa/people/:id`
### `POST /api/kansa/people/:id/upgrade`
### WebSocket: `wss://server/api/kansa/people/updates`
## Hugo Nominations
### `GET /api/hugo/:id/nominations`
### `POST /api/hugo/:id/nominate`
## Hugo Canonicalisation
### `GET /api/hugo/canon/canon`
### `GET /api/hugo/canon/nominations`
### `POST /api/hugo/canon/classify`
### `POST /api/hugo/canon/entry/:id`
### WebSocket: `wss://server/api/hugo/canon/updates`
