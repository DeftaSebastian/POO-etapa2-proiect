package outclass;

public final class ConsumerForDistributor {
    private long consumerId;
    private long price;
    private long remainedContractMonths;

    @Override
    public String toString() {
        return "consumerId=" + consumerId
                + ", price=" + price
                + ", remainedContractMonths=" + remainedContractMonths
                + '}';
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final long consumerId) {
        this.consumerId = consumerId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(final long price) {
        this.price = price;
    }

    public long getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final long remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}

