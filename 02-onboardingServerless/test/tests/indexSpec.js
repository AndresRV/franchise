'use strict';
const index = require('../../index.js');
const lambdaTestUtils = require('@nequi/nequi-ci-utils').Lambda8TestUtils;

describe('ONBOARDING_SERVERLESS/index.js', () => {

  it('index.js: Get Parameter', async () => {
    try {
      let response = await lambdaTestUtils.test(index.handler, 'test/success.json');
      const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
      expect(response).toBeDefined();
      expect(parameterRS).toBeDefined();
      expect(parameterRS).toBe("parametro1");
    } catch (error) {
      console.log('ERROR: ', error);
      expect(error).not.toBeDefined();
    }
  });

  it('index.js: Get Parameter Fail Validate Schema', async () => {
    try {
      const response = await lambdaTestUtils.test(index.handler, 'test/failValidateSchema.json')
      const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
      const { ResponseMessage: { ResponseHeader: { Status: { StatusCode } } } } = response;
      expect(response).toBeDefined();
      expect(parameterRS).toBeUndefined();
      expect(StatusCode).toBe("20-05A");
    } catch (error) {
      console.log('ERROR: ', error)
      expect(error).not.toBeDefined()
    }
  });  

  it('index.js: Get Parameter Fail Dynamo Service', async () => {
    try {
      const response = await lambdaTestUtils.test(index.handler, 'test/failDynamoService.json')
      const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
      const { ResponseMessage: { ResponseHeader: { Status: { StatusCode } } } } = response;
      expect(response).toBeDefined();
      expect(parameterRS).toBeUndefined();
      expect(StatusCode).toBe("20-07A");
    } catch (error) {
      console.log('ERROR: ', error)
      expect(error).not.toBeDefined()
    }
  });
}); 