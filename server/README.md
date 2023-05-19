## Request

GET /api/games?limit=8&offset=8

## MongoDB Query

db.game.find().projection({\_id: -1, gid: 1, name:1}).sort({gid: 1}).skip(8).limit(8)
