package com.blade.chatgpt.entity.fineTune;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HyperParam {

    @JsonProperty("batch_size")
    private Integer batchSize;
    @JsonProperty("learning_rate_multiplier")
    private Double learningRateMultiplier;
    @JsonProperty("n_epochs")
    private Integer nEpochs;
    @JsonProperty("prompt_loss_weight")
    private Double promptLossWeight;
}
