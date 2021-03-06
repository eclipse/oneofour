package org.eclipse.oneofour.asdu.message;

import org.eclipse.oneofour.ProtocolOptions;
import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.CommandValue;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.TypeHelper;

import io.netty.buffer.ByteBuf;

public abstract class AbstractSetPointCommandShortFloatingPoint extends AbstractInformationObjectMessage
{
    private final byte type;

    private final boolean execute;

    private final CommandValue<Float> value;

    private final boolean withTimestamp;

    public AbstractSetPointCommandShortFloatingPoint ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final CommandValue<Float> value, boolean withTimestamp, final byte type, final boolean execute )
    {
        super ( header, informationObjectAddress );
        this.value = value;
        this.type = type;
        this.execute = execute;
        this.withTimestamp = withTimestamp;
    }

    public byte getType ()
    {
        return this.type;
    }

    public CommandValue<Float> getValue ()
    {
        return this.value;
    }

    public boolean isExecute ()
    {
        return this.execute;
    }

    @Override
    public void encode ( final ProtocolOptions options, final ByteBuf out )
    {
        EncodeHelper.encodeHeader ( this, options, null, this.header, out );

        this.informationObjectAddress.encode ( options, out );

        out.writeFloat ( this.value.getValue () );

        byte b = 0;

        b |= this.type & 0b011111111;
        b |= this.execute ? 0 : 0b100000000;

        out.writeByte ( b );
        
        if ( withTimestamp )
        {
            TypeHelper.encodeTimestamp ( options, out, value.getTimestamp () );
        }
    }
}
