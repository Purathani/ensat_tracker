package ensat.unimelb.ensat_mobile_app.data;

/**
 * Created by purat on 28/09/2015.
 */
public class BioMaterialInfoTransfer {

    private int accBiomaterialId;
    private int accBiomaterialLocationId;
    private int accBiomaterialTransferGroupId;
    private int accBiomaterialTransferId;
    private String centerId;
    private String destinationCenterId;
    private int eensatId;
    private String status;
    private String transferedDate;

    public BioMaterialInfoTransfer() {
    }

    public BioMaterialInfoTransfer(int accBiomaterialId, int accBiomaterialLocationId, int accBiomaterialTransferGroupId, int accBiomaterialTransferId, String centerId, String destinationCenterId, int eensatId, String status, String transferedDate) {
        this.accBiomaterialId = accBiomaterialId;
        this.accBiomaterialLocationId = accBiomaterialLocationId;
        this.accBiomaterialTransferGroupId = accBiomaterialTransferGroupId;
        this.accBiomaterialTransferId = accBiomaterialTransferId;
        this.centerId = centerId;
        this.destinationCenterId = destinationCenterId;
        this.eensatId = eensatId;
        this.status = status;
        this.transferedDate = transferedDate;
    }

    public int getAccBiomaterialId() {
        return accBiomaterialId;
    }

    public void setAccBiomaterialId(int accBiomaterialId) {
        this.accBiomaterialId = accBiomaterialId;
    }

    public int getAccBiomaterialLocationId() {
        return accBiomaterialLocationId;
    }

    public void setAccBiomaterialLocationId(int accBiomaterialLocationId) {
        this.accBiomaterialLocationId = accBiomaterialLocationId;
    }

    public int getAccBiomaterialTransferGroupId() {
        return accBiomaterialTransferGroupId;
    }

    public void setAccBiomaterialTransferGroupId(int accBiomaterialTransferGroupId) {
        this.accBiomaterialTransferGroupId = accBiomaterialTransferGroupId;
    }

    public int getAccBiomaterialTransferId() {
        return accBiomaterialTransferId;
    }

    public void setAccBiomaterialTransferId(int accBiomaterialTransferId) {
        this.accBiomaterialTransferId = accBiomaterialTransferId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getDestinationCenterId() {
        return destinationCenterId;
    }

    public void setDestinationCenterId(String destinationCenterId) {
        this.destinationCenterId = destinationCenterId;
    }

    public int getEensatId() {
        return eensatId;
    }

    public void setEensatId(int eensatId) {
        this.eensatId = eensatId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransferedDate() {
        return transferedDate;
    }

    public void setTransferedDate(String transferedDate) {
        this.transferedDate = transferedDate;
    }
}

