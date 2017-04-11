package org.adridadou.ethereum.propeller.solidity.converters.decoders;

import org.adridadou.ethereum.propeller.values.EthData;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Type;

import static org.adridadou.ethereum.propeller.values.EthData.WORD_SIZE;

/**
 * Created by davidroon on 04.04.17.
 * This code is released under Apache 2 license
 */
public class StringDecoder implements SolidityTypeDecoder {
    private final NumberDecoder numberDecoder = new NumberDecoder();

    @Override
    public String decode(EthData word, EthData data, Type resultType) {
        Integer strIndex = numberDecoder.decode(word, data, Integer.class).intValue();
        Integer len = numberDecoder.decode(EthData.of(ArrayUtils.subarray(data.data, strIndex, strIndex + WORD_SIZE)), data, Integer.class).intValue();
        return new String(ArrayUtils.subarray(data.data, strIndex + WORD_SIZE, strIndex + WORD_SIZE + len));
    }

    @Override
    public boolean canDecode(Class<?> resultCls) {
        return String.class.equals(resultCls);
    }
}
