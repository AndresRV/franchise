const nequiUtils = require('@nequi/nequi-utils')
const nequiApiUtils = require('@nequi/nequi-api-utils')
const responseUtils = nequiApiUtils.ResponseAPIUtils
const RESPONSE_MESSAGES = nequiApiUtils.RESPONSE_MESSAGES
const env = nequiUtils.Environment
const lambdaUtils = nequiUtils.Lambda8
const dynamoService = require("../services/dynamoService");

const callService = async (event) => {
  try {

    const { RequestMessage: { RequestBody: { any: { parameterRQ } } } } = event;
    const parameter = await dynamoService.getParameter(event, parameterRQ);
    return parameter;

  } catch (error) {
    if (!!error && !!error.output) {
      throw error
    } else {
      throw lambdaUtils.buildOutput(true, true,
        getOutput(event, RESPONSE_MESSAGES.TECHNICAL_ERROR.CODE,
          RESPONSE_MESSAGES.TECHNICAL_ERROR.DESCRIPTION),
        'business', 'callService', error)
    }
  }
}

const getOutput = (event, code, description, body) => {
  return responseUtils.buildResponseFromRequest(event, code, description, body)
}

module.exports = async function processBusiness (event, request) {
  return await callService(event, request)
}