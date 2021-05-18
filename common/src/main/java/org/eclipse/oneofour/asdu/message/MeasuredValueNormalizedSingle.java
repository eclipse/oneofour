package org.eclipse.oneofour.asdu.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.oneofour.ProtocolOptions;
import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.ASDU;
import org.eclipse.oneofour.asdu.types.InformationEntry;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.InformationStructure;
import org.eclipse.oneofour.asdu.types.Value;

@ASDU ( id = 9, name = "M_ME_NA_1", informationStructure = InformationStructure.SINGLE )
public class MeasuredValueNormalizedSingle extends AbstractMeasuredValueNormalized
{
    private MeasuredValueNormalizedSingle ( final ASDUHeader header, final List<InformationEntry<Double>> entries )
    {
        super ( header, entries, false );
    }

    public static MeasuredValueNormalizedSingle parse ( final ProtocolOptions options, final byte length, final ASDUHeader header, final ByteBuf data )
    {
        return new MeasuredValueNormalizedSingle ( header, parseEntries ( options, length, data, false ) );
    }

    public static MeasuredValueNormalizedSingle create ( final ASDUHeader header, final InformationObjectAddress address, final Value<Double> value )
    {
        return createInternal ( header, Collections.singletonList ( new InformationEntry<> ( address, value ) ) );
    }

    public static MeasuredValueNormalizedSingle create ( final ASDUHeader header, final List<InformationEntry<Double>> values )
    {
        if ( values.size () > MAX_INFORMATION_ENTRIES )
        {
            throw new IllegalArgumentException ( String.format ( "A maximum of %s values can be transmitted", MAX_INFORMATION_ENTRIES ) );
        }
        return createInternal ( header, new ArrayList<> ( values ) );
    }

    private static MeasuredValueNormalizedSingle createInternal ( final ASDUHeader header, final List<InformationEntry<Double>> values )
    {
        return new MeasuredValueNormalizedSingle ( header, values );
    }

}
