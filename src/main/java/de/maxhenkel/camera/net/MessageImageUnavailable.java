package de.maxhenkel.camera.net;

import de.maxhenkel.camera.TextureCache;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class MessageImageUnavailable implements IMessage, IMessageHandler<MessageImageUnavailable, IMessage> {

    private UUID imgUUID;

    public MessageImageUnavailable() {

    }

    public MessageImageUnavailable(UUID imgUUID) {
        this.imgUUID = imgUUID;
    }

    @Override
    public IMessage onMessage(MessageImageUnavailable message, MessageContext ctx) {
        TextureCache.instance().addImage(imgUUID, new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        long l1 = buf.readLong();
        long l2 = buf.readLong();
        imgUUID = new UUID(l1, l2);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(imgUUID.getMostSignificantBits());
        buf.writeLong(imgUUID.getLeastSignificantBits());
    }


}
