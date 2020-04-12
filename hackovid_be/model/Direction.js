var mongoose = require('mongoose');  
var DirectionSchema = new mongoose.Schema({  
  address: String,
  city: String,
  province: String,
  postalCode: Number,
});
mongoose.model('Direction', DirectionSchema);

module.exports.Model = mongoose.model('Direction');
module.exports.Schema = DirectionSchema;