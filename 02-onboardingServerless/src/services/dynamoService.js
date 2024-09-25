const nequiDynamo = require('@nequi/nequi-aws-dynamodb');
const nequiUtils = require('@nequi/nequi-utils')
const nequiApiUtils = require('@nequi/nequi-api-utils')
const responseUtils = nequiApiUtils.ResponseAPIUtils
const RESPONSE_MESSAGES = nequiApiUtils.RESPONSE_MESSAGES
const env = nequiUtils.Environment
const lambdaUtils = nequiUtils.Lambda8

const ENV_NEQUI_PARAMETERS = "NEQUI_PARAMETERS";

const getParameter = async (event, request) => {
  try {

    const tableName = "nequi-parameters-qa";//env.getEnv(ENV_NEQUI_PARAMETERS);    
    const response  = await nequiDynamo.getItem(tableName, request);
    
    const { Item: { parameter } } = response;
    return parameter;

  } catch (error) { 
    throw lambdaUtils.buildOutput(true, true,
      getOutput(event, RESPONSE_MESSAGES.TECHNICAL_ERROR.CODE,
        RESPONSE_MESSAGES.TECHNICAL_ERROR.DESCRIPTION),
      'dynamoService', 'getParameter', error)
  }
}

const getOutput = (event, code, description, body) => {
  return responseUtils.buildResponseFromRequest(event, code, description, body)
}

module.exports = {
  getParameter: getParameter
}
