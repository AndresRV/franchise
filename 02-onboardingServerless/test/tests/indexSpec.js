'use strict';
const index = require('../../index.js');
const lambdaTestUtils = require('@nequi/nequi-ci-utils').Lambda8TestUtils;

describe('ONBOARDING_SERVERLESS/index.js', () => {

  it('index.js: Get Parameter', async () => {
    try {
      let response = await lambdaTestUtils.test(index.handler, 'test/success.json');

      const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
      const { ResponseMessage: { ResponseHeader: { Status: { StatusDesc } } } } = response;

      expect(response).toBeDefined();
      expect(parameterRS).toBeDefined();
      expect(parameterRS).toBe("parametro1");
      expect(StatusDesc).toBe("SUCCESS");
    } catch (error) {
      console.log('ERROR: ', error);
      expect(error).not.toBeDefined();
    }
  });

  it('index.js: Get Parameter Fail Validate Schema', async () => {
    try {
      const response = await lambdaTestUtils.test(index.handler, 'test/failValidateSchema.json');

      const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
      const { ResponseMessage: { ResponseHeader: { Status: { StatusCode, StatusDesc } } } } = response;

      expect(response).toBeDefined();
      expect(parameterRS).toBeUndefined();
      expect(StatusCode).toBe("20-05A");
      expect(StatusDesc).toBe("¡Parámetros incorrectos!");
    } catch (error) {
      console.log('ERROR: ', error)
      expect(error).not.toBeDefined()
    }
  });  

  it('index.js: Get Parameter Fail Table Key Empty', async () => {
    try {
      const response = await lambdaTestUtils.test(index.handler, 'test/failKeyEmpty.json');
      
      const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
      const { ResponseMessage: { ResponseHeader: { Status: { StatusCode, StatusDesc } } } } = response;

      expect(response).toBeDefined();
      expect(parameterRS).toBeUndefined();
      expect(StatusCode).toBe("20-05A");
      expect(StatusDesc).toBe("¡Parámetros incorrectos!");
    } catch (error) {
      console.log('ERROR: ', error)
      expect(error).not.toBeDefined()
    }
  });

  it('index.js: Get Parameter Fail Dynamo Service', async () => {
    try {
      let response = await lambdaTestUtils.test(index.handler, 'test/nonexistentTable.json');

      const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
      const { ResponseMessage: { ResponseHeader: { Status: { StatusCode, StatusDesc } } } } = response;

      expect(response).toBeDefined();
      expect(parameterRS).toBeUndefined();
      expect(StatusCode).toBe("20-08A");
      expect(StatusDesc).toBe("¡Dato no encontrado!");
    } catch (error) {
      console.log('ERROR: ', error)
      expect(error).not.toBeDefined()
    }
  });


  describe('With Changed Environment Variable', () => {
    const ORIGINAL_ENV = process.env;
    beforeAll(() => process.env.NEQUI_PARAMETERS = 'nequi-parameters-qaQW');
    afterAll(() => process.env = { ...ORIGINAL_ENV });

    it('index.js: Get Parameter Fail Dynamo Service', async () => {
      try {
        let response = await lambdaTestUtils.test(index.handler, 'test/success.json');
  
        const { ResponseMessage: { ResponseBody: { any: { parameterRS } } } } = response;
        const { ResponseMessage: { ResponseHeader: { Status: { StatusCode, StatusDesc } } } } = response;
  
        expect(response).toBeDefined();
        expect(parameterRS).toBeUndefined();
        expect(StatusCode).toBe("20-07A");
        expect(StatusDesc).toBe("¡Error técnico!");
      } catch (error) {
        console.log('ERROR: ', error)
        expect(error).not.toBeDefined()
      }
    });
  });
}); 