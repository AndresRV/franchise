'use strict'
/**
 * @module ONBOARDING_SERVERLESS
 * @description 02-onboardingServerless
 * @author arvilla <arvilla@nequi.com>
 * @version 1.0.0
 * @since 2024-09-24
 * @lastModified 2024-09-24
 */

const srcHandler = require('./src/handler/handler')

exports.handler = async (event, context) => {
  await srcHandler(event, context)
}
