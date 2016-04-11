package com.g4s.javelin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.ContractDTO;
import com.g4s.javelin.service.location.ContractService;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

/**
 * @author apadilla
 * @since 4/8/16.
 */
@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
                ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION)
public class ContractApi {

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.CONTRACT_SERVICE)
    private ContractService contractService;

    @ApiMethod(
            name = "contract.post",
            path = "contract/post",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void createNewContract(final ContractDTO contractDTO) {
        contractService.createNewContract(contractDTO);
    }

    @ApiMethod(
            name = "contract.list",
            path = "contract/list",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> getContracts() {
        return contractService.getContractsDTO();
    }

    @ApiMethod(
            name = "contract.number.search",
            path = "contract/number/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> getContractByNumber(@Named("contractNumber") final String contractNumber) {
        return contractService.getContractByNumber(contractNumber);
    }

    @ApiMethod(
            name = "contract.name.search",
            path = "contract/name/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> getContractByName(@Named("contractName") final String contractName) {
        return contractService.getContractByName(contractName);
    }


}
