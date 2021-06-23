package org.eclipse.oneofour.asdu.message;

import org.eclipse.oneofour.ProtocolOptions;
import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.CommandValue;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.TypeHelper;

import io.netty.buffer.ByteBuf;

public abstract class AbstractSingleCommand extends AbstractInformationObjectMessage
{
    private final CommandValue<Boolean> value;

    private final boolean withTimestamp;

    private final byte type;

    private final boolean execute;

    public AbstractSingleCommand ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final CommandValue<Boolean> value, final boolean withTimestamp, final byte type, final boolean execute )
    {
        super ( header, informationObjectAddress );
        this.value = value;
        this.withTimestamp = withTimestamp;
        this.type = type;
        this.execute = execute;
    }

    public byte getType ()
    {
        return this.type;
    }

    public boolean isState ()
    {
        return this.value.getValue ();
    }

    public boolean getState ()
    {
        return this.value.getValue ();
    }

    public CommandValue<Boolean> getValue ()
    {
        return value;
    }

    public boolean isExecute ()
    {
        return this.execute;
    }

    @Override
    public void encode ( final ProtocolOptions options, final ByteBuf out )
    {
        EncodeHelper.encodeHeader ( this, options, null, this.header, out );
        byte b = 0;

        b |= this.isState () ? 0b00000001 : 0;
        b |= this.type << 2 & 0b011111100;
        b |= this.execute ? 0 : 0b100000000;

        this.informationObjectAddress.encode ( options, out );

        out.writeByte ( b );

        if ( withTimestamp )
        {
            TypeHelper.encodeTimestamp ( options, out, value.getTimestamp () );
        }
    }
}
