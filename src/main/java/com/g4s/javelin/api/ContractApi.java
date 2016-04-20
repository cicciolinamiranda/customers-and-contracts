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

import javax.servlet.http.HttpServletRequest;

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

    /**
     * Save Contract
     *
     * @param contractDTO Contract object
     */
    @ApiMethod(
            name = "contract.save",
            path = "contract/save",
            httpMethod = ApiMethod.HttpMethod.POST)
    public ContractDTO saveContractDetails(final ContractDTO contractDTO) {
        ContractDTO response = contractService.saveContract(contractDTO);
        return response;
    }

    /**
     * Retrieve all contracts
     *
     * @return All contracts
     */
    @ApiMethod(
            name = "contract.list",
            path = "contract/all",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> getAllContracts() {
        return contractService.getContractsDTO();
    }

    /**
     * Retrieve all contracts by customerId
     *
     * @return All contracts
     */
    @ApiMethod(
            name = "contract.get.byCustomerId",
            path = "contract/customerId",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> getContractByCustomerId(@Named("id") final Long customerId) {
        return contractService.getContractByCustomerId(customerId);
    }

    /**
     * Search contract by number
     *
     * @param contractNumber Contract identification number
     * @return Contract using number
     */
    @ApiMethod(
            name = "contract.get.byNumber",
            path = "contract/number",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> getContractByNumber(@Named("contractNumber") final String contractNumber) {
        return contractService.getContractByNumber(contractNumber);
    }

    /**
     * Search contract by name
     *
     * @param contractName Contract name
     * @return Contract using name
     */
    @ApiMethod(
            name = "contract.get.byContractName",
            path = "contract/contractName",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> getContractByName(@Named("contractName") final String contractName) {
        return contractService.getContractByName(contractName);
    }

    /**
     *
     * @param searchTerm
     * @return
     */
    @ApiMethod(
            name = "contract.search",
            path = "contract/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContractDTO> searchAllContracts(@Named("searchterm") final String searchTerm) {
        List<ContractDTO> result = contractService.searchContract(searchTerm);
        return result;
    }

    /**
     * Init Contract
     *
     */
    @ApiMethod(
            name = "contract.init",
            path = "contract/init",
            httpMethod = ApiMethod.HttpMethod.GET)
    public ContractDTO initializeContract() {
        ContractDTO response = contractService.initializeContract();
        return response;
    }

}
