package org.eclipse.oneofour.asdu.message;

import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.CommandValue;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;

public interface ValueCommandMessage extends MirrorableMessage<ValueCommandMessage>
{
    public ASDUHeader getHeader ();

    public InformationObjectAddress getInformationObjectAddress ();

    public CommandValue<?> getValue ();

    public byte getType ();

    public boolean isExecute ();
}
