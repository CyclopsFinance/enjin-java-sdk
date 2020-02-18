package com.enjin.sdk.model.service.tokens;

import com.enjin.sdk.model.service.PaginationRequest;
import com.enjin.sdk.service.tokens.TokensService;

/**
 * The results of a get token request.
 *
 * @author Evan Lindsay
 * @see TokensService
 */
public class GetTokens extends PaginationRequest<GetTokens> {

    public GetTokens appId(Integer appId) {
        withParameter("appId", appId);
        return this;
    }

    /**
     * The token id.
     *
     * @param tokenId the token id.
     *
     * @return the builder.
     */
    public GetTokens tokenId(String tokenId) {
        withParameter("id", tokenId);
        return this;
    }

    /**
     * The token name.
     *
     * @param name the token name.
     *
     * @return the builder.
     */
    public GetTokens name(String name) {
        withParameter("name", name);
        return this;
    }

    /**
     * The token creator.
     *
     * @param creator creator ethereum address.
     *
     * @return the builder.
     */
    public GetTokens creator(String creator) {
        withParameter("creator", creator);
        return this;
    }

    /**
     * The total supply.
     *
     * @param totalSupply the total supply.
     *
     * @return the builder.
     */
    public GetTokens totalSupply(String totalSupply) {
        withParameter("totalSupply", totalSupply);
        return this;
    }

    /**
     * The reserve amount.
     *
     * @param reserve the reserve amount.
     *
     * @return the builder.
     */
    public GetTokens reserve(String reserve) {
        withParameter("reserve", reserve);
        return this;
    }

    /**
     * The supply model.
     *
     * @param supplyModel the supply model.
     *
     * @return the builder.
     */
    public GetTokens supplyModel(TokenSupplyModel supplyModel) {
        withParameter("supplyModel", supplyModel);
        return this;
    }

    /**
     * The melt value.
     *
     * @param meltValue the melt value.
     *
     * @return the builder.
     */
    public GetTokens meltValue(String meltValue) {
        withParameter("meltValue", meltValue);
        return this;
    }

    /**
     * The melt fee ratio.
     *
     * @param meltFeeRatio the fee ratio.
     *
     * @return the builder.
     */
    public GetTokens meltFeeRatio(String meltFeeRatio) {
        withParameter("meltFeeRatio", meltFeeRatio);
        return this;
    }

    /**
     * Whether non-fungible or not.
     *
     * @return the builder.
     */
    public GetTokens nonFungibleOnly() {
        withParameter("nonFungible", true);
        return this;
    }

    /**
     * The first block this token existed.
     *
     * @param firstBlock the block.
     *
     * @return the builder.
     */
    public GetTokens firstBlock(int firstBlock) {
        withParameter("firstBlock", firstBlock);
        return this;
    }

    /**
     * The block height of this token.
     *
     * @param blockHeight the block height.
     *
     * @return the builder.
     */
    public GetTokens blockHeight(int blockHeight) {
        withParameter("blockHeight", blockHeight);
        return this;
    }

    /**
     * Whether the token is marked for deletion or not.
     *
     * @return the builder.
     */
    public GetTokens markForDelete() {
        withParameter("markedForDelete", true);
        return this;
    }

}